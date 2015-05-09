#!/bin/bash

shopt -s expand_aliases

pwd

echo "----作業ディレクトリをクリーンします。----"
mvn clean:clean
echo "----作業ディレクトリをクリーンしました。----"

echo "----親プロジェクトのインストールを開始します。----"
mvn install -Dmaven.test.skip=true
echo "----親プロジェクトのインストールを完了しました。----"


echo "----プロジェクトのパッケージングを開始します。----"
cd ./reuse-calculator
mvn clean package -Prelease -Dmaven.test.skip=true -X
cd ..
echo "----プロジェクトのパッケージングを完了しました。----"

echo "----リリース用のapkを作成しました。----"
