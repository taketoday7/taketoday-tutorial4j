package cn.tuyucheng.taketoday.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import ratpack.http.client.HttpClient;
import ratpack.rx.RxRatpack;
import rx.Observable;

import java.net.URI;

public class HystrixReactiveHttpCommand extends HystrixObservableCommand<String> {

   private HttpClient httpClient;
   private URI uri;

   HystrixReactiveHttpCommand(HttpClient httpClient, URI uri, int timeoutMillis) {
      super(Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix-ratpack-reactive"))
            .andCommandPropertiesDefaults(HystrixCommandProperties
                  .Setter()
                  .withExecutionTimeoutInMilliseconds(timeoutMillis)));
      this.httpClient = httpClient;
      this.uri = uri;
   }

   @Override
   protected Observable<String> construct() {
      return RxRatpack.observe(httpClient
            .get(uri, requestSpec -> requestSpec.headers(mutableHeaders -> mutableHeaders.add("User-Agent", "Tuyucheng HttpClient")))
            .map(receivedResponse -> receivedResponse
                  .getBody()
                  .getText()));
   }

   @Override
   protected Observable<String> resumeWithFallback() {
      return Observable.just("eugenp's reactive fallback profile");
   }

}
