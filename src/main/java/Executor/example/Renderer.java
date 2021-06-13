package Executor.example;

import com.sun.org.apache.xpath.internal.functions.FuncTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static synchronization_tool_class.lactch.Preloader.launderThrowable;

/**
 * @author TerenceG
 * @classname Renderer
 * @description 使用CompletionService，使页面元素在下载完成后立即显示出来
 * @lastmodifydate 2021/6/13
 */
public class Renderer {
    private final ExecutorService executor;

    Renderer (ExecutorService executor){this.executor=executor;}

    void renderPage(CharSequence source){
        List<ImageInfo> infos=scanForImageInfo(source);
        CompletionService<ImageData> completionService=new ExecutorCompletionService<ImageData>(executor);
        for (final ImageInfo imageInfo:infos){
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        renderText(source);

        try{
            for(int t=0,n=infos.size();t<n;t++){
                Future<ImageData> f=completionService.take();
                ImageData imageData=f.get();
                renderImage(imageData);

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException ex){
            throw launderThrowable(ex.getCause());
        }
    }

    private void renderImage(ImageData imageData) {
        //未实现
    }
    private void renderText(CharSequence source) {
        //未实现
    }
    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return new ArrayList<ImageInfo>();
    }
}