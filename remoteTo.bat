@echo off
set who=%~1
if "%who%" == "github" (
 git remote set-url origin git@github.com:tonsonbin/baseapi.git
) ^
else if "%who%" == "aliyun" (
 git remote set-url origin git@code.aliyun.com:tangbin/baseApiC.git
)
git remote -v