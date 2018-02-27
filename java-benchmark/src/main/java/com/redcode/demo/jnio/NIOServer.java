package com.redcode.demo.jnio;

/**
 * Created by zhiyu.zhou on 2018/2/27.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO服务端
 */
public class NIOServer {

    private Selector selector;  //通道管理器

    public static void main(String[] args) throws IOException {

        NIOServer server = new NIOServer();

        server.initServer(8000);

        server.listen();
    }

    public void initServer(int port) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);  //NIO

        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        selector = Selector.open();
        //绑定通道和管理器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 轮询监听selector
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        while (true) {
            selector.select();

            Iterator iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();

                iterator.remove();

                //客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    //获取客户端连接的通道
                    SocketChannel channel = serverSocketChannel.accept();
                    channel.configureBlocking(false);//NIO
                    channel.write(ByteBuffer.wrap(new String("send message to client!").getBytes()));
                    channel.register(this.selector, SelectionKey.OP_ACCEPT);
                } else if (key.isReadable()) {  //获取了可读的事件
                    //读取对应通道的消息
                    read(key);
                }
            }
        }
    }

    public void read(SelectionKey key) throws IOException {
        //获取事件的socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(16);

        channel.read(buffer);
        //获取消息
        String msg = new String(buffer.array()).trim();
        System.out.println("server get the msg " + msg);
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(byteBuffer);
    }
}
