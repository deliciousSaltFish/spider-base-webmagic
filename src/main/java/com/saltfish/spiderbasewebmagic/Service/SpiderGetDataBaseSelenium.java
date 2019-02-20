package com.saltfish.spiderbasewebmagic.Service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Description:
 * 使用
 * spider-base-webmagic
 *
 * @Date: 2019/2/19 21:47
 * @Author: James Lin
 * @Version: 1.0
 */

public class SpiderGetDataBaseSelenium implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(3000)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36" +
                    " (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

    public static final String URL_LIST = "https://www\\.cnblogs\\.com/#p\\d{1,3}";

    public static int pageNum = 1;

    @Override
    public void process(Page page) {
        //爬取第一页
        if (page.getUrl().regex("^https://www\\.cnblogs\\.com$").match()) {
            try {
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"post_list\"]/div/div[@class='post_item_body']/h3/a/@href").all());

                pageNum++;
                page.addTargetRequest("https://www.cnblogs.com/#p2");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //爬取2-200页，一共有200页
        } else if (page.getUrl().regex(URL_LIST).match() && pageNum <= 5) {
            try {
                List<String> urls = page.getHtml().xpath("//*[@class='post_item']//div[@class='post_item_body']/h3/a/@href").all();
                page.addTargetRequests(urls);

                page.addTargetRequest("https://www.cnblogs.com/#p" + ++pageNum);
                System.out.println("CurrPage:" + pageNum + "######");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 获取页面需要的内容
            System.out.println("抓取的内容：" + page.getHtml().xpath("//a[@id='cb_post_title_url']/text()").get());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //配置文件，我用的webmagic0.7.2,低版本可能不需要该文件，但也不支持phantomjs.
        System.setProperty("selenuim_config", "D:/config.ini");
        //调用seleniumdownloader，这个downlaoder可以驱动selenium,phantomjs等方式下载，由config.ini配置
        Downloader downloader = new SeleniumDownloader();
        downloader.setThread(10);
        Spider.create(new SpiderGetDataBaseSelenium()).setDownloader(downloader).addUrl("https://www.cnblogs.com").thread(10).runAsync();
//        Spider.create(new ServiceGetData()).addUrl("https://www.cnblogs.com").thread(10).runAsync();
    }
}
