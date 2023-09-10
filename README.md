# 💡 Topic

- **여행 여정을 기록과 관리하는 SNS 서비스**
- 사용자가 여행 기록과 여행에 따른 여정을 기록할 수 있게 하는 서비스




# 📝 Summary

실제 많은 사람들은 여행을 다녀올 때 한 곳만을 다녀오지 않고 여러 개의 장소를 다녀오며 포스팅을 한다. 이런 점을 본따 사용자가 한 개의 여행을 기록하고 한 개의 여행 안에 여러 개의 장소를 기록할 수 있으며 JSON,CSV 형태로 기록을 남겨 JSON,CSV 파일을 통해 여행 전체를 조회하거나 특정 여행에서  다녀온 장소 여정들을 조회할 수 있도록 하는 서비스이다.




# ⭐️ Key Function

- 여행 기록
    - 사람들이 자신의 여행을 기록할 수 있다.
    - 하나의 여행 안에는 여러 개의 여정을 기록할 수 있다.
- 여정 기록
    - 여행 ID를 통하여 해당하는 여행 속에서 다녀온 장소들을 여정으로 기록할 수 있다.
- 여행 조회
    - 작성한 여행들을 조회해올 수 있다.
- 여정 조회
    - 하나의 여행에서 다녀온 여정들을 조회하고 싶을때 여행 ID를 통하여 조회해올 수 있다.



 

# 🛠 Tech Stack

`JAVA`, `Github`



# ⚙️ Architecture

`MVC`




# 🧑🏻‍💻 Team

- 백엔드 개발자 4명




# 🤚🏻 Part

- 서원빈 - View 구현
- 박건우 - Model 구현
- 박준모 - DTO,Entity 구현
- 장성수 - Controller 구현




# 🤔 Learned

- JSONParser의 동작 원리를 알고, 자바에서의 JSON,CSV 파일을 통한 읽고 쓰기를 알게 되었다.
- 인터페이스의 다형성을 통하여 효과적인 클래스 구성을 할 수 있게 되었다.
- MVC 구조를 고려한 구현으로 각 역할들이 분리되어 있어 코드의 구성을 논리적으로 유지하고 관리할 수 있게 되었다.




# 🙋🏻‍♂️ Question

프로젝트간 질문거리나 코드리뷰를 받고 싶은 부분들에 대한 내용입니다.

- 중복되는 로직들을 최대한 모듈화 시켜서 리팩토링을 진행하고 싶은데 중복되는 코드들 로직이 짧다면 어떻게 리팩토링을 하는게 좋을까요?
- Util 클래스 생성에 대해서는 의견이 사람마다 다른 것 같은데, 만약 한 클래스에서만 쓰이는 로직이더라도 별도의 Util 클래스로 만드는 방식이 괜찮나요? 보편적으로 추구하는 방식이 궁금합니다.




# 🗣️ 추가사항

- ‘기획안’ 폴더에 해당 프로젝트의 기획안, API명세서, 시스템 아키텍쳐 자료가 들어있습니다.
- ‘구동 모습’ 폴더에도 구동장면 스크린샷들이 들어있습니다.




# 📷 Screenshot

# 메인 화면

![메인화면](https://github.com/Parkgeonmoo/SNS_Toy_Project/assets/50697545/3cf7e4b7-4737-4331-aac3-bbc25ec26775)


# (1) 여행기록

![(1)여행기록](https://github.com/Parkgeonmoo/SNS_Toy_Project/assets/50697545/74c1d3d2-a58d-46da-a8f3-468d26f988b2)


# (2)여정기록
![(2)여정기록](https://github.com/Parkgeonmoo/SNS_Toy_Project/assets/50697545/828a8bbe-4712-4908-9cbc-c1cfd1f30779)


# (3)여행조회
![(3)여행조회](https://github.com/Parkgeonmoo/SNS_Toy_Project/assets/50697545/8a449359-c410-411f-b065-8cc78625ef8d)


# (4) 여정조회

![(4)여정조회](https://github.com/Parkgeonmoo/SNS_Toy_Project/assets/50697545/bc639cba-ef7d-4f9e-94de-9d95ac13b206)


# (5)종료
![(5)종료](https://github.com/Parkgeonmoo/SNS_Toy_Project/assets/50697545/0c86d7a2-0964-4089-a732-c981365f89cb)
