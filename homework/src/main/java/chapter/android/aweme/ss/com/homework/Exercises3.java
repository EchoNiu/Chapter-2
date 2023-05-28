package chapter.android.aweme.ss.com.homework;

import android.app.Activity;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity {
    private List<Message> messages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        //从data.xml中解析消息数据
        try {
            InputStream inputStream = getAssets().open("data.xml");
            messages = PullParser.pull2xml(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //找到消息ListView
        ListView messageListView = findViewById(R.id.rv_list);

        //创建消息适配器
        MessageAdapter adapter = new MessageAdapter(this, messages);

        //设置适配器
        messageListView.setAdapter(adapter);
    }

    private class MessageAdapter extends BaseAdapter {
        //getView()方法返回每个消息项的视图
        private Activity activity;
        private List<Message> messages;

        public MessageAdapter(Activity activity, List<Message> messages) {
            this.activity = activity;
            this.messages = messages;
        }
        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //复用convertView
            if (convertView == null) {
                convertView = LayoutInflater.from(Exercises3.this).inflate(R.layout.im_list_item, parent, false);
            }

            //设置消息内容
            TextView contentTextDescription = convertView.findViewById(R.id.tv_description);
            contentTextDescription.setText(messages.get(position).getDescription());

            TextView contentTextTime = convertView.findViewById(R.id.tv_time);
            contentTextTime.setText(messages.get(position).getTime());

            TextView contentTextTitle = convertView.findViewById(R.id.tv_title);
            contentTextTitle.setText(messages.get(position).getTitle());

            //设置消息用户头像
            CircleImageView avatarImageView = convertView.findViewById(R.id.iv_avatar);
            avatarImageView.setImageResource((R.drawable.session_stranger));

            //返回消息视图
            return convertView;
        }

    }


}
