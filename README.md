## 기능 구현 목록

### 입력

-[x] 콘솔에서 문자열 입력
-[x] 빈 입력 처리 시 기본값 ("")반환

### 검증

-[x] 커스텀 구분자 형식 검증 (//[구분자]\n)
-[x] 구분자가 1개의 문자인지 검증
-[x] 숫자가 양의 정수인지 검증 (음수, 0, 소수 예외처리)
-[x] 입력값이 비어 있는지 검증
-[x] 구분자와 숫자 외의 문자 존재 검증

### 파싱

-[x] 커스텀 구분자 추출
-[x] 본문 추출 (커스텀 구분자 이후의 문자열)
-[x] 구분자 목록 구성
    -[x] 기본 구분자: 쉼표(,), 콜론(:)
    -[x] 커스텀 구분자
-[x] 구분자를 이용한 본문 문자열 분리
-[x] 분리된 문자열 정수 리스트로 변환
-[x] 변환 과정 중 파싱 불가능한 값 존재 시 예외 발생

### 계산

-[x] 주어진 숫자 리스트의 합 계산
-[x] 빈 리스트의 경우 0 반환

### 출력

-[x] "결과 : {숫자}" 형태로 출력

---

## 프로그램 실행 흐름

### 전체 아키텍처

```
Application (진입점)
    ↓
CalculatorController (MVC - Controller)
    ├─ InputView (사용자 입력)
    ├─ Calculator (도메인 로직)
    └─ OutputView (결과 출력)
```

### 실행 단계

#### 1. 입력 (InputView)

- 콘솔에서 문자열 입력 받기
- 빈 입력 시 빈 문자열("") 반환

#### 2. 파싱 (InputParser)

- 커스텀 구분자 감지: "//"로 시작하는지 확인
- 커스텀 구분자 추출: "//"와 "\n" 사이의 문자
- 커스텀 구분자 검증: 1개의 문자인지 확인 (Delimiter)
- 본문 추출: "\n" 이후의 숫자 문자열

#### 3. 구분자 구성 (Delimiters)

- 기본 구분자: 쉼표(,), 콜론(:)
- 커스텀 구분자 추가 (있는 경우)
- 정규식 생성: 모든 구분자를 "|"로 연결

#### 4. 문자열 분리 (Delimiters.split)

- 정규식을 이용한 문자열 분리
- 빈 토큰 검증: 연속 구분자 예외 처리

#### 5. 숫자 변환 (Numbers.from)

- 각 토큰을 trim 후 PositiveInteger로 변환
- PositiveInteger 검증: 양의 정수(≥ 1)인지 확인
- 숫자가 아니거나 0 이하면 IllegalArgumentException 발생

#### 6. 계산 (Numbers.sum)

- List<PositiveInteger>의 합계 계산
- 빈 리스트인 경우 0 반환

#### 7. 출력 (OutputView)

- "결과 : {숫자}" 형식으로 출력

### 예시 실행

**입력:** `//;\n1;2,3:4`

```
InputParser.parse("//;\n1;2,3:4")
  → ParseResult { delimiters: [",", ":", ";"], numberText: "1;2,3:4" }

Delimiters.split("1;2,3:4")
  → ["1", "2", "3", "4"]

Numbers.from(["1", "2", "3", "4"])
  → Numbers([PositiveInteger(1), PositiveInteger(2), PositiveInteger(3), PositiveInteger(4)])

Numbers.sum()
  → 10
```

**출력:** `결과 : 10`

---