# ⚡️ 게시글 조회 성능 최적화 - 인덱스 적용 (개인스터디)

대량의 게시글 데이터를 조회할 때 **Full Table Scan**으로 인한 성능 저하 문제를 해결하고자,  
`board_id` 컬럼에 인덱스를 적용하여 조회 성능을 개선했습니다.


<br />

## ✅ 성능 테스트 결과

| 구분             | 응답시간   | 설명                             |
|------------------|------------|----------------------------------|
| 🔴 인덱스 적용 전   | **1718ms** | Full Table Scan 발생             |
| 🟢 인덱스 적용 후   | **473ms**  | 인덱스 사용 → 성능 향상 |

> 📌 *조회 성능을 **약 3.6배** 개선했습니다 (1718ms → 473ms)*

<br />

---

### 1️⃣ 인덱스 적용 전 실행 계획
```sql
EXPLAIN SELECT * FROM post WHERE board_id = 1 ORDER BY create_at DESC LIMIT 10 OFFSET 20;
```
<img width="714" alt="image" src="https://github.com/user-attachments/assets/ccddbf75-64fc-48ba-ac3c-11623c9791a8" />

> EXPLAIN 결과, 게시글을 조회할 때 전체 테이블을 스캔하는 Full Table Scan이 발생함
  
<br /><br />
### 2️⃣ 인덱스 생성
```sql
CREATE INDEX idx_board_id_created_at ON post(board_id, create_at DESC);
```
<img width="716" alt="image" src="https://github.com/user-attachments/assets/a86cda0c-9126-483f-a392-00c6878ac923" />

> board_id 컬럼에 인덱스를 생성
  
<br /><br />
### 3️⃣ 인덱스 적용 확인
```sql
SHOW INDEX FROM post;
```
<img width="718" alt="image" src="https://github.com/user-attachments/assets/020d43d5-0d54-4341-b1f7-d26ce796ed00" />

> SHOW INDEX FROM post; 명령어를 통해 idx_board_id 인덱스가 정상 생성되었음을 확인

<br /><br />
### 4️⃣ 인덱스 적용 후 실행 계획
```sql
EXPLAIN SELECT * FROM post WHERE board_id = 1 ORDER BY create_at DESC LIMIT 10 OFFSET 20;
```
<img width="1052" alt="image" src="https://github.com/user-attachments/assets/24173b93-43f1-4413-933a-1346bd5eebb0" />

> 게시글 조회 시 type이 ref, key에 idx_board_id가 명시됨
>
> 실제로 인덱스를 사용하여 검색 범위를 줄인 것을 확인
  
<br /><br />
### 5️⃣ 응답 시간 비교
<img width="715" alt="image" src="https://github.com/user-attachments/assets/bcb2defc-a0c1-4d84-8fff-ffed8b3d704b" />
<br />

> 🔺 인덱스 적용 전 - 전체 테이블 스캔으로 인해 응답 시간 지연 발생 (1718ms)
  
<br />
<img width="715" alt="image" src="https://github.com/user-attachments/assets/f2b66fe2-74c4-4a1e-bdd5-7a17cb375ec2" />
<br />

> 🔺 인덱스 적용 후 - 인덱스를 활용해 성능이 획기적으로 개선 (473ms)
