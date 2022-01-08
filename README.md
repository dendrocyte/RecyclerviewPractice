# RecyclerviewPractice

This is RecyclerView Practice about implementing varient UI design.
Use Build varient to manage different UI design implementation.


## Build Varient
Note: if you change to new build varient or open my project, variant's buildConfig is not created.
Just skip the error and build it. It's not affect preformance.
After you run the project, BuildConfig will be created, then this error will disappear.


#### LinearVer [=LinearVertical layout] varient
- Use native adapter
- Dynamic modify the height per item

![image]()

#### GridVer [=GridVertical layout] varient
- Use native adapter
- Dynamic modify the height per item
- Dynamic modify the width per item via SpanSize</br>
-> make the first header to be one row, the others header is the same size with items

![image]()

#### StaggerVer [=StaggerVertical layout] varient
- Use native adapter
- Fixed height of header and random height of items
 
![image]()

#### GridHor [=GridHorizontal layout] varient
- Use native adapter
- Add divider and margin dynamically via ItemDecoration</br>

![image]()



