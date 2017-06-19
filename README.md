# ListView 的通用适配器


## 未进行封装以前，一个ListView 或GridView 的Adapter 代码结构如下：

``` java
public class MyAdapter extends BaseAdapter  
{  
    private LayoutInflater mInflater;  
    private Context mContext;  
    private List<String> mDatas;  
  
    public MyAdapter(Context context, List<String> mDatas)  
    {  
        mInflater = LayoutInflater.from(context);  
        this.mContext = context;  
        this.mDatas = mDatas;  
    }  
  
    @Override  
    public int getCount()  
    {  
        return mDatas.size();  
    }  
  
    @Override  
    public Object getItem(int position)  
    {  
        return mDatas.get(position);  
    }  
  
    @Override  
    public long getItemId(int position)  
    {  
        return position;  
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent)  
    {  
        ViewHolder viewHolder = null;  
        if (convertView == null)  
        {  
            convertView = mInflater.inflate(R.layout.item_single_str, parent,  
                    false);  
            viewHolder = new ViewHolder();  
            convertView.setTag(viewHolder);  
        } else  
        {  
            viewHolder = (ViewHolder) convertView.getTag();  
        }  
        // 绑定视图....
        return convertView;  
    }  
  
    private final class ViewHolder  
    {  
        // your field 
    }  
  
}  
```

## 如果创建多个Adapter 多是重复书写的代码，需要进行封装

### 关于数据源 mDatas ，使用泛型声明  `private List<String> mDatas;`  

###  getCount() 、getItemId() 、getItem() 保持原本的实现方式

``` java
  @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
```

 ### 重点在于 getView()方法，这个方法的本意就是获取需要显示的视图，那就把视图的创建和绑定的职责交给ViewHolder来完成

``` java
  @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      //创建ViewHolder 是否创建新的由ViewHolder 自身去决定
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(mContext, convertView, parent, mItemViewId, position);
      //ViewHolder 进行绑定视图的操作
        convert(commonViewHolder, getItem(position), position);
      //返回需要显示的视图
        return commonViewHolder.getConvertView();
    }
```

###  ViewHolder 

``` java
 public static CommonViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {//判断是否有可以复用的 convertView
            return new CommonViewHolder(context, parent, layoutId, position);
        }
        return (CommonViewHolder) convertView.getTag();
    }

 private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();  //用于保存convertView 需要操作的View
   //渲染视图
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
   //setTag 操作 以备复用
        mConvertView.setTag(this);
        this.mContext = context;
    }

```

## 封装之后使用的方式

``` java
 listView.setAdapter(new ListCommonAdapter<TestBean>(this, mDatas, R.layout.item_listview) {
            @Override
            protected void convert(CommonViewHolder commonViewHolder, TestBean item, int position) {
              //commonView 中封装了一系列简单的视图操作，并且可以链式调用
                commonViewHolder
                        .setImageResource(R.id.iv_icon, item.getImageId())
                        .setText(R.id.tv_content, item.getContent());
            }
        });
```



## License

    Copyright 2016 Rúben Sousa

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
