package com.moziqi.testmvvm;

import cn.ittiger.net.common.RequestCallback;
import cn.ittiger.net.http.TigerOkHttp;
import cn.ittiger.net.http.exeception.TigerHttpException;
import cn.ittiger.net.http.request.TigerJsonRequest;

/**
 * MVP的深入learn 其他人的框架https://github.com/moziqi/Beam
 * Created by moziqi on 16-5-1.
 */
public class MainPresenter implements IPresenter {

    private IMainView iMainView;

    private TigerJsonRequest<String> request;

    private Thread netThread;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
        netThread = new Thread(this);
    }

    public void testGet() {
        request = new TigerJsonRequest<>("https://www.baidu.com");
        //request.addHeaders(MApplication.getInstance().getDefaultHeaders());
        request.setRequestCallback(new RequestCallback<String>() {
            @Override
            public void onSuccess(String result) {
                iMainView.doSuccess(result);
            }

            @Override
            public void onFailure(TigerHttpException e) {
                iMainView.doFailure(e.getMessage());
            }
        });
        netThread.start();
    }

    @Override
    public void run() {
        //http://blog.csdn.net/aminfo/article/details/7903112
        TigerOkHttp.getAsync(request);
    }

    @Override
    public void cancel() {
        TigerOkHttp.cancel(request.getTag());
        request = null;
        netThread = null;
    }
}
