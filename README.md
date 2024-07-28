# 상품재고 차감 API

## 목차
- [개발 스펙](#개발-스펙)
- [주요 사항](#주요-사항)
- [API 명세](#api-명세)
    - [상품 관련 API](#상품-관련-api)
    - [상품재고 관련 API](#상품재고-관련-api)
    - [공통 응답 예시](#공통-응답-예시)
- [CSV 파일 처리](#csv-파일-처리)
- [프로젝트 패키지 구조](#프로젝트-패키지-구조)
- [Table 정보](#table-정보)

---

### 개발 스펙
* SpringBoot 3.3.3
* JAVA 21
* JPA
* Querydsl
* H2 Database
---

### 주요 사항
1. 주어진 CSV 파일에 대해 `Spring Batch`를 통해 Read 및 Write(=DB 저장)한다.
2. DB는 주어진 과제 환경 특성 상, `H2 Database`를 활용한다.
3. SQL 쿼리는 `JPA` 및 `Querydsl`을 통해 CRUD 한다.
4. 문제로 주어진 과제 API에 대해 특성 별로 Controller를 분리한다.
    1. `ProductController` : `상품` 관련 CRUD API
    2. `InventoryController` : `상품 재고` 관련 CRUD API
5. 모든 API 반환값은 `responseDto<T>`로 공통 처리한다.
5. 모든 API는 `Swagger`를 통해 명세서를 작성한다.
---

### API 명세
* Swagger : http://localhost:8080/swagger-ui/index.html
---

### 상품 관련 API
* [`POST`/`PUT`/`GET`/`DELETE`] ~/product/v1/unit : 상품 단건 CRUD API
* [`POST`/`PUT`/`GET`] ~/product/v1/units : 상품 복수건 CRU API
* [`GET`] ~/product/v1/category/{category} : 대분류 상품 리스트 조회 API
* [`GET`] ~/product/v1/category/{category}/sub-category/{subcategory} : 소분류 상품 리스트 조회 API

---
### 상품 재고 관련 API
* [`POST`/`PUT`/`GET`/`DELETE`] ~/product/inventory/v1/unit : 상품재고 단건 CRUD API
* [`GET`] ~/product/inventory/v1/units : 상품재고 복수건 조회 API
* [`PUT`] ~/product/inventory/v1/decrease/unit : 상품재고 차감 API

---
### 공통 응답 예시
```http
200 OK

{
    "status": 200,
    "message": OK,
    "data": {
        ...
    }
}
```
```http
500 FAIL

{
    "status": 500,
    "message": FAIL,
    "data": {
        ...
    }
}
```
---
### CSV 파일 처리

### Job: `csvFileSaveJob`

### 기능
- **목표**
    - (Spring Batch) CSV 파일의 데이터를 읽고 DB 저장하는 Job 구현 및 자동 실행
- **구성**
    - `csvFileReader` : CSV 파일 Read
    - `csvFileWriter` : CSV 파일에 대한 DB 저장
---

### 프로젝트 패키지 구조
`batch` : **CSV 파일에 대한 DB 저장**을 위한 spring batch 관련 패키지

`config` : 각 **Configuration bean** 설정을 위한 패키지

`input` : API **초입부** 패키지 (Controller 관련 정보)

`inventory` : **메인 비즈니스 로직** 관련 패키지

`output` : **API 반환** 및 **외부**로 나가는 로직에 대한 패키지

---
### Table 정보

#### products (상품 정보)
| Column Name    | Description        | Key       | etc                      |
|----------------|--------------------|-----------|--------------------------|
| `product_id`   | 상품ID (Product Id)  | **PK**    |                          |
| `product_name` | 상품명 (Product Name) |           |                          |
| `description`  | 설명 (Description)   |           | **UNKNOWN**으로 통일(데이터 제약) |
| `category`     | 대분류 (Category)     |           |                          |
| `sub_category` | 소분류 (Sub Category) |           |                          |
| `price`        | 가격 (Price)         |           |                          |
| `description`  | 설명 (Description)   |           |                          |
| `regpe_id`     | 등록자ID (Regpe Id)   |           | SYSTEM (default)         |
| `reg_dts`      | 등록일시 (Reg Dts)     |           | yyyy-MM-dd'T'HH:mm:ss    |
| `modpe_id`     | 수정자ID (Modpe Id)   |           | SYSTEM (default)                |
| `mod_dts`      | 수정일시 (Mod Dts)     |           | yyyy-MM-dd'T'HH:mm:ss    |

#### price_inventory (상품재고 정보)
| Column Name  | Description         | Key    | etc                      |
|--------------|---------------------|--------|--------------------------|
| `product_id` | 종목코드 (Product Id)   | **PK** |                          |
| `quantity`   | 재고량 (Quantity)      |        |                          |
| `regpe_id`     | 등록자ID (Regpe Id)   |           | SYSTEM (default)         |
| `reg_dts`      | 등록일시 (Reg Dts)     |           | yyyy-MM-dd'T'HH:mm:ss    |
| `modpe_id`     | 수정자ID (Modpe Id)   |           | SYSTEM (default)                |
| `mod_dts`      | 수정일시 (Mod Dts)     |           | yyyy-MM-dd'T'HH:mm:ss    |

