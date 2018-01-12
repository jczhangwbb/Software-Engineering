# Software-Engineering
NTUST Software Engineering
如果發生方塊無法下落的bug，可將Controller中253行～256行註釋，即可運行。 
註釋內容為：
```
level = score /5 +1;
timer.setDelay(500 - level * 20);
gameBoard.setLevelText(String.valueOf(level+" "));
```
