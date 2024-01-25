@echo off
setlocal enabledelayedexpansion

rem Specify the folder where the markdown files are located
set "folder_path=C:\Users\KIIT\Desktop\Projects\Java\Parsedown\src"

rem Iterate through each markdown file in the folder and run the Java program
for %%f in ("%folder_path%\*.md") do (
    echo Processing file: %%f
    java Parsedown "%%f"
)

rem Keeping the Command Prompt window open
pause