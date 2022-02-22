# 테스트 방법

## 실행
```
./gradlew bootRun -x test
```

## API 호출

### 1. rest-api.http 이용

### 2. curl 이용
#### 장소검색
```shell
curl -v  "http://localhost:8080/v1/place" \
  --data-urlencode "q=수내역 은행"
```

#### 검색키워드목록
```shell
curl -v  "http://localhost:8080/v1/keyword"
```
