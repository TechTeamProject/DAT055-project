package src;

import java.util.LinkedList;

public class ToDo {
    private class task {
        private String title;
        private boolean marked;

        private task(String title_, boolean marked_){
            title = title_;
            marked = marked_;
        }

        private String getTitle(){
            return title;
        }

        private boolean getMarked(){
            return marked;
        }

        private void setMarked(boolean marked_){
            marked = marked_;
        }
    }

    private LinkedList<task> ToDoList = new LinkedList<task>();

    public ToDo(){
        this.createToDo("Test", false);
        this.createToDo("Test1", false);
        this.createToDo("Test2", false);
        this.createToDo("Test3", false);
        this.createToDo("Test4", false);
        this.createToDo("Test5", false);
        this.createToDo("Test6", false);
        this.createToDo("Test7", false);
    }

    public void createToDo(String title_, boolean marked_){
        ToDoList.add(new task(title_, marked_));
    }

    //Depends on how the todolist is implemented, if it contains indexes of the things.
    public void removeToDo(String title_) {
        for(int i = 0; i < ToDoList.size(); i++){
            if(ToDoList.get(i).getTitle().equals(title_)){
                ToDoList.remove(i);
            }
        }

        //ToDoList.remove(index);
    }

    public int getIndexToDo(String title_){
        for(int i = 0; i < ToDoList.size(); i++){
            if(ToDoList.get(i).getTitle().equals(title_)){
                return i;
            }
        }
        return -1;
    }

    public String getTitleToDo(int index){
        return ToDoList.get(index).getTitle();
    }

    public int getSizeList(){
        return ToDoList.size();
    }

    //Marks/Unmarks the ToDo.
    public void markToDo(int index){
        ToDoList.get(index).setMarked(!ToDoList.get(index).getMarked());
    }

    //Removes all marked todos
    public void removeMarked(){
        for(int i = 0; i < ToDoList.size(); i++){
            if(ToDoList.get(i).getMarked()){
                //removeToDo(i);
                i--;
            }
        }
    }
}
