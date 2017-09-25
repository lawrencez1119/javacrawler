package demo;
import java.awt.event.ItemEvent;
import javax.rmi.CORBA.Tie;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class jsoupdemo {
	public static void main(String[] args) throws IOException{
		String url="https://www.oschina.net/news";
		Document document=Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.91 Safari/537.36")
				.get();
		
		Elements items=document.select("#all-news .item");
		System.out.println(items.size());
		String host="https://www.oschina.net";
		for(Element item:items)
		{
			//过滤广告
			if(!item.attr("data-trscepid").isEmpty())
			{
				continue;
			}
			//标题
			String title=item.select("a").first().text();//得到第一个匹配元素的文本内容
			//标题地址
			String title_href=item.select("a").first().attr("href");
			if(!title_href.startsWith("https://"))
			{
				title_href=host+title_href;
			}
			
			//描述
			String desc=item.select("div[class =sc sc-text text-gradient wrap summary]").text();
			
			//作者头像
			String img=item.select("img[class=avatar]").attr("src");
			
			Element mr=item.select(".from .mr").get(0);
			
			//作者
			String author=mr.select("a").text();
			//发布时间
			mr.select("a").remove();
			String pubtime=mr.text();
			//评论数
			String number=item.select("a").last().text();
			
			System.out.println("标题："+title);
			System.out.println("地址："+title_href);
			System.out.println("描述："+desc);
			System.out.println("作者头像地址："+img);
			System.out.println("作者："+author);
			System.out.println("发布时间："+pubtime);
			System.out.println("评论数："+number);
			System.out.println();
			System.out.println();
		}
	}
}

/*import java.io.IOException;

import javax.rmi.CORBA.Tie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 
 * 类名称：News   
 * 类描述： 抓取开源中国新闻列表  
 * 创建人：geekfly
 * 创建时间：2017年7月19日 下午8:54:46      
 * @version  V1.0
 *
 */
/*public class jsoupdemo {

	public static void main(String[] args) throws IOException {
		String url = "https://www.oschina.net/news"; 
		Document document = Jsoup.connect(url) 
		.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0") 
		.get(); 
		
		Elements items = document.select("#all-news .item");
		System.out.println(items.size());
		String host = "https://www.oschina.net";
		for(Element item: items){
			//过滤广告
			if(!item.attr("data-tracepid").isEmpty()){
				continue;
			}
			// 标题
			String title = item.select("a").first().text();
			
			//标题地址
			String title_href = item.select("a").first().attr("href");
			if(!title_href.startsWith("https://")){
				title_href = host + title_href;
			}
			//描述
			String desc = item.select("div[class=sc sc-text text-gradient wrap summary]").text();
			
			//作者头像
			String author_image = item.select("img[class=avatar]").attr("src");
			//String author_image = item.select("img").first().attr("src");
			
			Element mr = item.select(".from .mr").get(0);
			//作者
			String author = mr.select("a").text();
			// 从span[class=mr]中移除a标签，输出的即为发布时间
			mr.select("a").remove();
			String published = mr.text();
			
			String number = item.select(".from .mr").last().text();
			System.out.println(number);
			
		}

	}

}*/