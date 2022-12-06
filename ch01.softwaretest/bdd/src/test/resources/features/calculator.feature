Feature: 계산기

  Scenario Outline: 두 수 더하기
    Given 계산기 객체를 생성한다
    When 두 수 <x>과 <y>을 더한다
    Then 결과는 <z>이어야 한다
    Examples:
      | x    | y    | z     |
      | 10   | 20   | 30    |
      | 1    | 2    | 3     |
      | -10  | 20   | 10    |
      | 0    | 0    | 0     |