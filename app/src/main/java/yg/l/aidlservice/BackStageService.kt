package yg.l.aidlservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.blankj.utilcode.util.ProcessUtils

/**
 *@PackageName: yg.l.aidlservice
 *@Class: BackStageService
 *@Author: lyg
 *@Date: 2021/12/17-10:55
 *@Description: 用于进程间通讯的后台服务
 */
class BackStageService : Service() {
    var myBinder: MyBinder? = null
    override fun onBind(intent: Intent?): IBinder? {
        if (myBinder == null) {
            myBinder = MyBinder()
        }
        Log.e("PROCESS", "当前进程名称：${ProcessUtils.getCurrentProcessName()}")
        return myBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("AIDL", "服务已启动！")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AIDL", "服务销毁！")
    }

}