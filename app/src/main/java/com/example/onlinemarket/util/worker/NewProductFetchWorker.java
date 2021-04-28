package com.example.onlinemarket.util.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.onlinemarket.data.repository.ProductRepository;

public class NewProductFetchWorker extends Worker {

    public NewProductFetchWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Do the work here--in this case, fetch last product
        //pollFromServerAndNotify();

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }
    @Override
    public void onStopped() {
        super.onStopped();
    }


    public static void pollFromServerAndNotify(Context context,String tag){
        ProductRepository productRepository = ProductRepository.getInstance();
        productRepository.fetchLatest();
    }


}
