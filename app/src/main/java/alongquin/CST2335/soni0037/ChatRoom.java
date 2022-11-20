package alongquin.CST2335.soni0037;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import alongquin.CST2335.soni0037.data.ChatRoomViewModel;
import alongquin.CST2335.soni0037.databinding.ActivityChatRoomBinding;
import alongquin.CST2335.soni0037.databinding.ReceiveMessageBinding;
import alongquin.CST2335.soni0037.databinding.SentMessageBinding;
import kotlinx.coroutines.channels.Receive;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    ChatRoomViewModel chatModel;

    private RecyclerView.Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        messages = chatModel.messages.getValue();

        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(click -> {
            messages.add(new ChatMessage(binding.textInput.getText().toString(),new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a").format(new Date()), true));
            myAdapter.notifyItemInserted(messages.size()-1 );
            //clear the previous text
            binding.textInput.setText("");
                });

        binding.receiveButton.setOnClickListener(click -> {
            messages.add(new ChatMessage(binding.textInput.getText().toString(),new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a").format(new Date()), false));
            myAdapter.notifyItemInserted(messages.size()-1 );
            //clear the previous text
            binding.textInput.setText("");
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if(viewType != 1) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }   else {
                    ReceiveMessageBinding receiveMessageBinding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(receiveMessageBinding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.getMessage());
                holder.timeText.setText(obj.getTimeSent());
            }

            @Override
            public int getItemCount() {

                return messages.size();
            }

            @Override
            public int getItemViewType(int position){
                if (messages.get(position).isSentButton()) {
                    return 0;
                } else {
                    return 1;
                }

            }
        });
    }
}

class MyRowHolder extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView timeText;

    public MyRowHolder(@NonNull View itemView) {

        super(itemView);
        messageText = itemView.findViewById(R.id.message);
        timeText = itemView.findViewById(R.id.time);
    }
}