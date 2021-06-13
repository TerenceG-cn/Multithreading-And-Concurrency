package Executor.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static synchronization_tool_class.lactch.Preloader.launderThrowable;

/**
 * 使用Future等待图像下载
 */
public class FutureRender {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<ImageData>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };
        //负责下载图像
        Future<List<ImageData>> future = executor.submit(task);
        //负责渲染文本
        renderText(source);

        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }

        } catch (InterruptedException e) {
            //重新设置线程中断状态
            Thread.currentThread().interrupt();
            //由于不需要结果，因此取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    private void renderText(CharSequence source) {
    }


    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        //未实现
        return new ArrayList<ImageInfo>();
    }

    private void renderImage(ImageData data) {
    }
}
