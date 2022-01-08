# RecyclerviewPractice

This is RecyclerView Practice about implementing varient UI design.
Use Build varient to manage different UI design implementation.


## Build Varient
Note: if you change to new build varient or open my project, variant's buildConfig is not created.
Just skip the error and build it. It's not affect preformance.
After you run the project, BuildConfig will be created, then this error will disappear.</br>
<img src="https://github.com/dendrocyte/RecyclerviewPractice/blob/main/recording/ChangeBuildVarient.mp4" width="100" height="100">


### LinearVer [=LinearVertical layout] varient
- Use native adapter
- Dynamic modify the height per item

<img src="https://github.com/dendrocyte/RecyclerviewPractice/blob/main/screenshot/LinearVer.png" width="257" height="447">

### GridVer [=GridVertical layout] varient
- Use native adapter
- Dynamic modify the height per item
- Dynamic modify the width per item via SpanSize</br>
-> make the first header to be one row, the others header is the same size with items

![image](https://github.com/dendrocyte/RecyclerviewPractice/blob/main/recording/GridVertical.mp4)

### StaggerVer [=StaggerVertical layout] varient
- Use native adapter
- Fixed height of header and random height of items
 
![image](https://github.com/dendrocyte/RecyclerviewPractice/blob/main/recording/StaggerVertical.mp4)

### GridHor [=GridHorizontal layout] varient
- Use native adapter
- Add divider and margin dynamically via ItemDecoration</br>

![image](https://github.com/dendrocyte/RecyclerviewPractice/blob/main/recording/GridHor.mp4)



