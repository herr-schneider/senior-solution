package apeh;

import apeh.CaseType;
import apeh.CaseTypeDto;
import apeh.CreateAppointmentCommand;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ApehService {

    private AtomicLong id = new AtomicLong();
    private ModelMapper modelMapper;
    private List<CaseType> types = new ArrayList<>();

    public List<CaseType> listTypes() {
        Type targetType = new TypeToken<List<CaseTypeDto>>() {
        }.getType();
        return modelMapper.map(types, targetType);
    }

    public boolean validCaseID(String caseID) {
//        return types.stream()
//                .map(CaseType::getType)
//                .anyMatch(type -> type.equals(caseID))


        return types.stream().anyMatch(type -> type.getType().equals(caseID));
    }

    public CaseTypeDto addAppointment(CreateAppointmentCommand command) {

    }

//    public CaseTypeDto addAppointment(CreateAppointmentCommand command) {
//        types.add(new CaseType(command.getTitle(), )
//return
//    }
}
