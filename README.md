# PictureManagerDemo

## 概要
写真管理サービスの一部機能(API)の実装

## 課題にかけた時間
おおよそ8~9時間　（開発環境の作成とSpring Bootの勉強時間を含めて）

## 環境
* Java 8
* Maven
* H2 (インメモリデータベース)
* IDE: InteliJ

## フレームワーク
* Spring Boot
* Hibernate (JPA)
* Guava
* Lombok
  * Constructor, Getter, Setter, equals, hashcodeなどアノテーションを使って自動的に生成しますので、コードがもっと読みやすいになります
  
## ローカルでの動かし方
### 1. IDE

**[必須要件]** InteliJでLombokというプラグインが必要です

* InteliJにプロジェクトを開きます
* Mavenのclean installを実行します
* 下記のクラスをRUNします：com.pantkiewicz.demo.picturemanager.PictureManagerDemoApplication

### 2. コマンドライン

**[必須要件]** ローカルでJava 8とMavenがインストールされています
* GitHubからプロジェクトのZIPアーカイブをダウンロードして、解凍します。
* コマンドラインを開いて、解凍されたディレクトリをワーキングディレクトリにします
* 下記のコマンドを実行します
```
mvn package
mvn install
java -jar target\picture-manager-0.0.1-SNAPSHOT.jar
```

## テストデータ

プロジェクトのスタートアップの時に予めテストデータをロードします。
```
com.pantkiewicz.demo.picturemanager.config.StartupDataLoader
```

作成したデータは下記の通りです(全てのテーブルに自動生成IDカラムもあります)。


### USERS
```
LOGIN | PASSWORD
----------------
patryk  qwerty123
john    abc123
```
### EVENTS
```
   DATE     |         NAME        | DESCRIPTION
------------------------------------------------
2019-04-28      Culture festival  
2019-05-05      Children's day
```

### PICTURES
```
   EVENT_ID          |    NAME     |     FILE_PATH
-------------------------------------------------------
[Culture festival]       PIC_001     /test/PIC_001.png
[Culture festival]       PIC_002     /test/PIC_002.png
[Culture festival]       PIC_003     /test/PIC_003.png
[Children's day]         PIC_004     /test/PIC_004.png
[Children's day]         PIC_005     /test/PIC_005.png
```

### ORDERS
```
   USER_ID  | ORDERNO  |     DATE    |          
--------------------------------------        
[patryk]       OR01      2019-04-28  
[john]         OR02      2019-04-30 
[patryk]       OR03      2019-05-06
[john]         OR04      2019-05-07
```

### ORDER_PICTURES
```
   ORDER_ID  | PICTURE_ID          
----------------------------      
   [OR01]       [PIC_001]      
   [OR01]       [PIC_003]   
   [OR02]       [PIC_002]      
   [OR02]       [PIC_003]  
   [OR03]       [PIC_004]      
   [OR04]       [PIC_004]   
   [OR04]       [PIC_005]   
```


## API
### ユーザがログインするAPI
> [POST] http://localhost:8080/user/login

* パスワードがハッシュとしてデータベースに保存されています
* loginIDは大文字小文字を区別しません

#### 引数の例
```
{
	"loginID": "patryk",
	"pass": "qwerty123"
}
```
#### 戻り値の例
```
{
    "result": "OK",
    "statusMessage": "Login successful",
    "userID": "1",
    "errors": []
}
```

### 写真購入枚数の集計API
> [POST] http://localhost:8080/order/searchOrderedPicturesForPeriod
#### 引数の例
```
{
	"startDate" : "2017-04-01",
	"endDate" : "2019-05-25"
}
```
#### 戻り値の例
```
{
    "result": "OK",
    "statusMessage": null,
    "orderedPictures": [
        {
            "eventID": "3",
            "eventName": "Culture festival",
            "pictureID": "5",
            "pictureName": "PIC_001",
            "count": "1"
        },
        {
            "eventID": "3",
            "eventName": "Culture festival",
            "pictureID": "6",
            "pictureName": "PIC_002",
            "count": "1"
        },
        {
            "eventID": "3",
            "eventName": "Culture festival",
            "pictureID": "7",
            "pictureName": "PIC_003",
            "count": "2"
        },
        {
            "eventID": "4",
            "eventName": "Children's day",
            "pictureID": "8",
            "pictureName": "PIC_004",
            "count": "2"
        },
        {
            "eventID": "4",
            "eventName": "Children's day",
            "pictureID": "9",
            "pictureName": "PIC_005",
            "count": "1"
        }
    ],
    "errors": []
}
```
