# StudentManagementAPI

***

## 概要

***

- このAPIは、中学校および高校の学生データを管理するためのCRUD処理を実装しています。

## 開発環境

***

- 言語
    - Java17
- フレームワーク
    - Spring Boot 3.2.3
- データベース
    - MySQL
- テスト
    - JUnit
    - Mockito
    - AssertJ
    - Database Rider
- バージョン管理
    - Git
    - GitHub
- サーバー
    - AWS

## 使用ツール

***

- IntelliJ
- Docker
- Docker Compose
- Postman

## データベース概要

***

| カラム名(論理名) | カラム名(物理名)   | 型・桁         | Nullable | その他コメント                               | 
|-----------|-------------|-------------|----------|---------------------------------------|
| ID        | id          | int         | No       | PRIMARY KEY・ UNSIGNED・ AUTO_INCREMENT |
| 名前        | name        | VARCHAR(20) | Yes      |                                       |
| 学年        | grade       | VARCHAR(20) | Yes      |                                       |
| 出身地       | birth_place | VARCHAR(20) | Yes      |                                       |

## 実装機能

***

### 実装機能の種類

| No | CRUD   | エンドポイント                               | 機能        | 機能について                                 |
|----|--------|---------------------------------------|-----------|----------------------------------------|
| ①  | Read   | GET /students/{id}                    | 学生ID参照    | 指定したIDの学生のデータを参照します                    |
| ②  | Read   | GET students                          | 全学生参照     | 全学生のデータを参照します                          |
| ③  | Read   | GET /students?grade={grade}           | 学年参照      | URLのクエリパラメータ検索で指定した学年の学生のデータを参照します     |
| ④  | Read   | GET /students?startsWith={startsWith} | 頭文字参照     | URLのクエリパラメータ検索で指定した名前の頭文字の学生のデータを参照します |
| ⑤  | Read   | GET /students?birthPlace={birthPlace} | 出身地参照     | URLのクエリパラメータ検索で指定した出身地の学生のデータを参照します    |
| ⑥  | Create | POST /students                        | 新規学生登録    | 新しい学生を登録します                            |
| ⑦  | Update | PATCH /students/{id}                  | 学生IDデータ更新 | 指定した学生のデータを更新します                       |
| ⑧  | update | PATCH /students/grade/_batchUpdate    | 全学生学年更新   | 全学生の学年を一斉に更新します                        |
| ⑨  | delete | DELETE /students/{id}                 | 学生ID削除    | 指定したIDの学生のデータを削除します                    |

### API仕様書

***
APIの詳細な仕様については、以下のSwagger UIを参照してください。

[API仕様書](https://mizoguchi-kouichi.github.io/assignment8/)

## レスポンスハンドリング

***

このAPIは、以下のようなエラー状況に対してレスポンスを返します。

| ステータスコード                   | 説明                   | 状態  |
|----------------------------|----------------------|-----|
| 200  OK                    | リクエストが正常に処理できた       | 正常  |
| 201  Created               | リクエストが成功してリソースの作成が完了 | 正常  |
| 400  Bad Request           | 一般的なクライアントエラー        | エラー |
| 404  Not Found             | Webページが見つからない        | エラー |
| 500  Internal Server Error | 何らかのサーバ内で起きたエラー      | エラー |

## AWS構成図

![StudentManagementAWS構成図.drawio (1).png](..%2F..%2F..%2FDownloads%2FStudentManagementAWS%E6%A7%8B%E6%88%90%E5%9B%B3.drawio%20%281%29.png)

## デプロイ状況

- Postmanで確認しました。

## 工夫した点

- リクエストをする流れをController・Service・Mapperの3種類のクラスに分ける事で  
  クライアント側・アプリ内の処理・sqlクエリと役割が分かりやすい構造にしました。
-

リクエストの型の間違いはController層でエラーレスポンスを返し、データベースにデータが存在しない場合や、登録・更新時のカラム入力の間違い等の  
アプリケーション内のエラーは、Service層でエラーレスポンスを返すように工夫しました。

- 各メソッドにJavaDocによる説明文を追加しました。

## 課題になった点

- バリデーションエラーレスポンスボディを作成で自分がしたいレスポンスボディに作成出来ず悩みました。
- テストコードの効率化のため、@ParameterizedTestと@CsvSourceを活用したアプローチを試みました。  
  参考資料が限られていましたが、JUnit公式ドキュメントを詳細に研究することで、最終的に実装に成功しました。
- API仕様書の実装過程で、不明点に遭遇した際、ChatGPTや技術記事での調査が多くなっていました。  
  より効率的な解決のためには、早期に質問や相談することの大切さを知りました。

## 今後の展望

- フロント側の実装