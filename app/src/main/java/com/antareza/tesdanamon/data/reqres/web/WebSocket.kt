package com.antareza.tesdanamon.data.reqres.web

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class WebSocketListener : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        webSocket.send("Hello, it's me. I'm still here")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        callbackData(text.toString())
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        webSocket.close(CODE_NORMAL, null)
        callbackData("onWebsocketClosing $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        callbackData("onWebsocketError ${t.message}")
    }

    private fun callbackData(msg: String) {
        Log.e("TAG", "callbackData: $msg")
    }

    companion object {
        private const val CODE_NORMAL = 1000
    }

}