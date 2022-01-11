package yg.l.aidlservice

/**
 *@PackageName: yg.l.aidlservice
 *@Class: MyBinder
 *@Author: lyg
 *@Date: 2021/12/17-10:09
 *@Description: AIDL 的 Stub 实现方法
 */
class MyBinder : MyAidlService.Stub() {
    override fun basicTypes(
        anInt: Int,
        aLong: Long,
        aBoolean: Boolean,
        aFloat: Float,
        aDouble: Double,
        aString: String?
    ) {

    }

    /**
     * aidl 自定义与其他进程通讯的方法
     * 这个就是我们自定义的，实现我们的方法，传值给客户端，
     * 如果不同的app进程接收到了，说明这次的IPC通讯成功了
     */
    override fun getBookName(): String = "服务端消息, "
}