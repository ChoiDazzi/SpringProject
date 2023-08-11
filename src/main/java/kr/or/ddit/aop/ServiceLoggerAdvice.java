package kr.or.ddit.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
    + Aspect: AOP 의 단위가 되는 횡단 관심사
    + 횡단관심사(Cross-Cutting Concern): 핵심 비즈니스 로직 (core)와는 거리가 있지만 여러 모듈에서 공통적이고 반복적인 처리를 요구하는 내용
    + 횡단관심사 분리(Seperation of Cross-Cutting Concern): 횡단 관심사에 해당하는 부분을 분리해서 한 곳으로 모음
    + Component: 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 어노테이션
    + JoinPoint: 클라이언트가 호출하는 모든 비즈니스 메소드, 조인포인트 중에서 포인트컷되기 때문에 포인트컷의 후보로 생각할 수 있다.
               : 어드바이스가 적용될 수 있는 위치
    + Pointcut : 특정 조건에 의해 필터링된 조인포인트, 수많은 조인포인트 중에 특정 메소드에서만 횡단 공통기능을 수행시키기 위해서 사용한다.
    + Advice : 횡단 관심에 해당하는 공통 기능의 코드, 독립된 클래스의 메소드로 작성한다
             : 어떤 부가기능을 언제 사용할지 정의
             [ 어드바이스의 동작 시점 ]
             - Before: JoinPoint(메서드 실행) 전에 실행
             - After: JoinPoint 에서 처리가 완료된 후 실행
             - Around: JoinPoint 전후에  실행
             - After Returning: JoinPoint가 정상적으로 종료 후 실행
             - After Throwing: JoinPoint에서 에외 발생 시 실행. 예외가 발생 안되면 실행 안 됨
    + AOP대상 (로그, 보안, 트랜잭션, 에러)
 */
@Slf4j
@Component
@Aspect
@EnableAspectJAutoProxy
public class ServiceLoggerAdvice {
    /*
        PointCut 표현식 *..*(..)
        *   : 임의의 1개의 리턴 타입
        ..  : 임의의 0개 이상
        kr.or.ddit.*..*(..)
        *   - 패키지 아래의 각각의 패키지가 있고
        ..  - 그 하위에 모든 파일/패키지
        *   - 각각의 메서드
        (..) : 모든 파라미터
     */

    @Before("execution(* kr.or.ddit.*..*(..))")
    public void startLog(JoinPoint jp) {
        log.info("startLog");
        //.getSignature() : 어떤 클래스의 어떤 메서드가 실행되었는지, 파라미터 타입은 무엇인지 보여줌
        log.info("startLog={}", jp.getSignature());
        //.getArgs() :
        log.info("startLog={}", Arrays.toString(jp.getArgs()));
    }
}
