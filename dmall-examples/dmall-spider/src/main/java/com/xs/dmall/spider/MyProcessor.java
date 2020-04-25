package com.xs.dmall.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 爬取类
 */
public class MyProcessor implements PageProcessor {

    /**
     * Page代表了从Downloader下载到的一个页面——可能是HTML，也可能是JSON或者其他文本格式的内容。
     * Page是WebMagic抽取过程的核心对象，它提供一些方法可供抽 取、结果保存等。
     *
     * @param page
     */
    @Override
    public void process(Page page) {
//        System.out.println(page.getHtml().toString());
        //指定xpath来抓取网页的部分内容
//        System.out.println(page.getHtml().xpath(""));

        //将当前页面里的所有链接都添加到目标页面中
        page.addTargetRequests(page.getHtml().links().all());
        System.out.println("-----");

        /*
           目标地址正则匹配
           需求：只提取播客的文章详细页内容，并提取标题
         */
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a‐z 0‐9‐]+/article/details/[0‐9]{8}").all());
        System.out.println(page.getHtml().xpath("//* [@id=\"mainBox\"]/main/div[1]/div[1]/h1/text()").toString());
    }

    /**
     * Site用于定义站点本身的一些配置信息，例如编码、HTTP头、超时时间、重试策略 等、代理等，都可以通过设置Site对象来进行配置。
     *
     * @return
     */
    @Override
    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }

    public static void main(String[] args) {
        //1
//        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").run();

        //2
        // 需求：只提取播客的文章详细页内容，并提取标题
//        Spider.create( new MyProcessor()).addUrl("https://blog.csdn.net/nav/ai").run();

        //3. ConsolePipeline 控制台输出
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").addPipeline(new ConsolePipeline()).run();

        //4. FilePipeline 文件保存
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").addPipeline(new ConsolePipeline()).addPipeline(new FilePipeline("e:/data"));//以文件方式保存.run();

        //5. 以json方式保存
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").addPipeline(new ConsolePipeline()).addPipeline(new FilePipeline("e:/data")).addPipeline(new JsonFilePipeline("e:/json"));// 以json方式保存.run();

        //6. 定制Pipeline
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").addPipeline(new ConsolePipeline())
                .addPipeline(new FilePipeline("e:/data")).addPipeline(new JsonFilePipeline("e:/json"))
                .addPipeline(new MyPipeline());//定制化输出.run();

        /*
          7. 每次运行可能会爬取重复的页面，这样做是没有任何意义的。Scheduler(URL管理) 最基本的功能是实现对已经爬取的URL进行标示。可以实现URL的增量去重。
            1）内存队列 QueueScheduler
            2）文件队列FileCacheQueueScheduler
            3) Redis队列 RedisScheduler
         */
        // 使用setScheduler来设置Scheduler
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").setScheduler(new QueueScheduler()).run();
        // 使用文件保存抓取URL，可以在关闭程序并下次启动时，从之前抓取到的URL继续抓取
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net")
                //.setScheduler(new QueueScheduler())//设置内存队列
                .setScheduler(new FileCacheQueueScheduler("E:\\scheduler")).run();//设置文件队列
        // 使用Redis保存抓取队列，可进行多台机器同时合作抓取
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net")
                .setScheduler(new RedisScheduler("127.0.0.1")).run();//设置Redis队列

    }
}
