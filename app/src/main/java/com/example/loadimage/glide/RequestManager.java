package com.example.loadimage.glide;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tangqipeng
 * @date 2020/9/2 6:48 PM
 * @email tangqipeng@aograph.com
 */
public class RequestManager {

    private LinkedBlockingQueue<BitmapRequest> blockingQueue = new LinkedBlockingQueue<>();
    private BitmapDispather[] dispathers;



    public static RequestManager getInstance(){
        return Holding.manager;
    }

    public static class Holding{
        public static RequestManager manager = new RequestManager();
    }

    public RequestManager() {
        creadAndStartDispatchers();
    }

    public void addBitmapRequest(BitmapRequest request){
        if (!blockingQueue.contains(request)){
            blockingQueue.add(request);
        }
    }

    private void creadAndStartDispatchers(){
        int threadCount = Runtime.getRuntime().availableProcessors();
        dispathers = new BitmapDispather[threadCount];
        for (BitmapDispather dispather : dispathers){
            BitmapDispather bitmapDispather = new BitmapDispather(blockingQueue);
            bitmapDispather.start();
            dispather = bitmapDispather;
        }
    }

}
