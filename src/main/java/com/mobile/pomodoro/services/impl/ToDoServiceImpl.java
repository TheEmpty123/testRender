package com.mobile.pomodoro.services.impl;

import com.mobile.pomodoro.dto.request.ToDoRequestDTO;
import com.mobile.pomodoro.dto.response.MessageResponseDTO;
import com.mobile.pomodoro.dto.response.ToDoResponseDTO;
import com.mobile.pomodoro.dto.response.ToDoResponseDTO.SingleToDoDTO;
import com.mobile.pomodoro.entities.Todo;
import com.mobile.pomodoro.entities.User;
import com.mobile.pomodoro.repositories.ToDoRepository;
import com.mobile.pomodoro.services.IToDoService;
import com.mobile.pomodoro.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl extends AService implements IToDoService {
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private IUserService IUser;
    @NonNull
    HttpServletRequest request;

    ToDoServiceImpl(){
        initData();
    }

    @Override
    public void initData() {
        log.setName(this.getClass().getSimpleName());
        log.info("Initializing data");
    }

    @Override
    public ToDoResponseDTO getAllTodosByUserId(Long userId) {
        List<Todo> todos = toDoRepository.findByUserId(userId);

        List<SingleToDoDTO> todoDTOs = todos.stream()
                .map(todo -> SingleToDoDTO.builder()
                        .id(todo.getToDoId())
                        .title(todo.getTitle())
                        .is_done(todo.getIsDone())
                        .build())
                .collect(Collectors.toList());

        return ToDoResponseDTO.builder()
                .list(todoDTOs)
                .build();
    }

    @Override
    public MessageResponseDTO createToDo(ToDoRequestDTO requestDTO, User user) {
        log.info("Tạo todo mới từ: " + user.getUsername());
        try {
            Todo todo = Todo.builder()
                    .userId(user.getUserId())
                    .title(requestDTO.getTitle())
                    .isDone(0)
                    .build();
            toDoRepository.save(todo);
            log.info("Tạo todo thành công cho user id: " + user.getUserId());
            return MessageResponseDTO.builder()
                    .message("Succeed")
                    .build();

        } catch (Exception e) {
            log.error("Lỗi khi thêm ToDo: " + e.getMessage(), e);
            return MessageResponseDTO.builder()
                    .message("Failed")
                    .build();
        }
    }

    @Transactional
    @Override
    public MessageResponseDTO updateToDo(Long todoId, ToDoRequestDTO requestDTO, User user) {
        log.info("Yêu cầu cập nhật từ: " + user.getUsername() + " với todo id: " + todoId);
        try {
            Todo todo = toDoRepository.findByIdAndUserId(todoId, user.getUserId())
                    .orElseThrow(() -> new Exception("Todo không tồn tại hoặc không thuộc về user"));
            todo.setTitle(requestDTO.getTitle());
            todo.setIsDone(requestDTO.getIsDone());
            log.info("Todo sau cập nhật: title = " + todo.getTitle() + ", isDone = " + todo.getIsDone());
            toDoRepository.save(todo);
            log.info("Cập nhật thành công cho user id: " + user.getUserId());
            return MessageResponseDTO.builder()
                    .message("Succeed")
                    .build();

        } catch (Exception e) {
            log.error("Lỗi khi cập nhật ToDo: " + e.getMessage(), e);
            return MessageResponseDTO.builder()
                    .message("Failed")
                    .build();
        }
    }
    @Override
    public MessageResponseDTO deleteToDo(Long todoId, ToDoRequestDTO requestDTO, User user) {
        log.info("Yêu cầu xóa todo từ: " + user.getUsername() + " với todo id: " + todoId);
        try {
            Todo todo = toDoRepository.findByIdAndUserId(todoId, user.getUserId())
                    .orElseThrow(() -> new Exception("Todo không tồn tại hoặc không thuộc về user"));
            toDoRepository.delete(todo);
            log.info("Xóa thành công todo id: " + todoId);
            return MessageResponseDTO.builder()
                    .message("Succeed")
                    .build();

        } catch (Exception e) {
            log.error("Lỗi khi xóa ToDo: " + e.getMessage(), e);
            return MessageResponseDTO.builder()
                    .message("Failed")
                    .build();
        }
    }
}
