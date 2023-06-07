package hello.example.core.web;

import hello.example.core.common.MyLogger_Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    1. @RequiredArgsConstructor 로 인해서 생성자가 호출된다.
//    2. MyLogger 는 @Scope("request") 로 인해서 요청이 들어오지 않아 에러가 발생한다.
//    3. ObjectProvider<MyLogger> 를 변수로 변경을 해주어서 오류를 잡아준다.
    private final ObjectProvider<MyLogger_Provider> myLoggerProvider;

    public void logic(String id) {
        MyLogger_Provider myLoggerProvider = this.myLoggerProvider.getObject();
        myLoggerProvider.log("service id = " + id);
    }
}
