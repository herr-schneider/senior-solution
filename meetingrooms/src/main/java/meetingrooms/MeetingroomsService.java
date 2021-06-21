package meetingrooms;

public class MeetingroomsService {
    private MeetingroomsRepository meetingroomsRepository;

    public MeetingroomsService(MeetingroomsRepository meetingroomsRepository) {
        this.meetingroomsRepository = meetingroomsRepository;
    }

    public void create(String name, int length, int width){
        meetingroomsRepository.create(name, width, length);
    }

public String listABC(){
        return meetingroomsRepository.listABC().toString();
}

public String reservedListABC(){
    return meetingroomsRepository.reservedListABC().toString();
}

public void evenList(){}

public void areaList(){

}

public void search(){}

public void partialSearch(){}

public void areaSearch(){}


}
