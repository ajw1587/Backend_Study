package hello.example.core.web;

import hello.example.core.common.MyLogger_Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    1. @RequiredArgsConstructor 로 인해서 생성자가 호출된다.
//    2. MyLogger 는 @Scope("request") 로 인해서 요청이 들어오지 않아 에러가 발생한다.
//    3. ObjectProvider<MyLogger> 를 변수로 변경을 해주어서 오류를 잡아준다.
//          - MyLogger Bean 은 이미 컨테이너에 등록이 되어 있고 Provider 를 통해 새로운 빈을 만들어준다.
//    private final MyLogger myLogger;
    private final ObjectProvider<MyLogger_Provider> myLoggerProvider;

    @RequestMapping("log-demo") // log-demo 라는 요청이 오면
    @ResponseBody   // 뷰화면 없이 문자를 반환
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURI().toString();

        MyLogger_Provider myLoggerProvider = this.myLoggerProvider.getObject();
        myLoggerProvider.setRequestURL(requestURL);

        myLoggerProvider.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
