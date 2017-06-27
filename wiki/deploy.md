# 项目部署说明
	- 1、命令行切换到工程根目录
	- 2、执行命令 mvn -f pom-deploy.xml package -Dbatch.module=${batchModule}
	- 3、batchModule为batch模块名称，如此工程中的bat00X,打包时使用的命令为： mvn -f pom-deploy.xml -Dbatch.module=00X
	- 4、生成的jar在target目录下，文件名为bat+${batchModule}.jar
	- 5、cd到target执行命令，java -jar bat+${batchModule}.jar,即可看到控制台输出了batch的log，表示执行成功