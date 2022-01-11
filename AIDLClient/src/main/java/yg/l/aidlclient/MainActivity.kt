package yg.l.aidlclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import yg.l.aidlservice.MyAidlService

class MainActivity : AppCompatActivity() {
    var aidlService: MyAidlService? = null
    // 创建连接监听通道
    var serviceConnection: ServiceConnection = Connection()
    val TAG = "AIDL"
    var b = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainScope().launch {
            // 创建意图，连接服务端的Service，启动Service
            // 当服务端使用的 SDK 是 31 时，外界的程序无法绑定服务
            var intent = Intent()
            intent.action = "yg.l.aidlservice.BackStageService"
            intent.setPackage("yg.l.aidlservice")
            b = applicationContext.bindService(intent, serviceConnection, BIND_AUTO_CREATE)
        }

        get.setOnClickListener {
            runCatching {
                text.text = aidlService?.bookName
                Log.e(TAG, "===================获取到的书名：${aidlService?.bookName} $b")
            }.onFailure {
                Log.e(TAG, "错误警示：${it.printStackTrace()}")
            }
        }
    }

    inner class Connection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            aidlService = MyAidlService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e(TAG, "===$name")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext.unbindService(serviceConnection)
    }
}