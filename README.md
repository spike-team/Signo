# Sīgno [![Build Status](https://travis-ci.com/spike-team/Signo.svg?branch=master)](https://travis-ci.com/spike-team/Signo)
StudenTable 서비스의 백엔드 프로젝트입니다.

# 프로젝트 소개
**어원**

라틴어로 sīgno는 '글자를 쓰다', '기록하다'라는 뜻이 있습니다. 이러한 사전적 의미처럼 학생과 교사의 시간표 관련 정보를 소중하게 기록하고 관리하는 프로젝트의 목적에 따라 Sīgno로 프로젝트의 이름을 정하게 되었습니다.

**기능**

Sīgno 프로젝트는 전국 시·도교육청 관할 초·중·고등학교에서 학급의 시간표를 무료로 관리할 수 있는 프로젝트인 StudenTable 서비스의 백엔드 프로젝트입니다. 다음과 같은 기능을 지원합니다.

- 전국 시·도교육청 관할 초·중·고등학교 학교코드 조회
- 학급 시간표 관리
  - 교사 및 과목, 특별실 등의 정보를 입력받아 자동으로 시간표 생성
    - 기존 시간표를 csv 형식으로 불러올 수 있음
    - 블럭 타임, 체육관·운동장, 교사 희망 시간대까지 계산
    - 별도의 수작업이 필요치 않아 교사의 업무 능률 향상 기대
  - 생성된 시간표를 교육행정정보시스템(나이스)에 쉽게 적용할 수 있도록 csv 파일 지원
  - 교사 간 시간표 변경이 발생할 때 빠르게 수정 가능
    - 시간표 변경 발생시 FCM을 이용, 앱·웹 클라이언트에 알림 전송
  - 대(對)학생 공지, 동료 교사들에게 전달하기 위한 다양한 문서 생성 자동화
- 급식표 ~~및 학사일정~~ 확인

**기술 스택**

- Spring Boot
- MongoDB
- Firebase Cloud Messaging
- TravisCI