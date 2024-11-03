# 使い方

## 起動手順

### DB起動

~~~shell
docker-compose up -d
~~~

### テーブル作成

jooqプロジェクト配下のflywayMigrateタスクを実行

### ORマッピング用のクラス作成

 jooqプロジェクト配下のjooqGenerateタスクを実行

### アプリ起動

GraphqlApplication実行（SpringBoot起動）
