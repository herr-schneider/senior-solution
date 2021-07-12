package musicStore;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private List<Instrument> instrumentList = new ArrayList<>();
    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;

    public MusicStoreService(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Integer> price) {
//        Type targetType = new TypeToken<List<InstrumentDTO>>() {
//        }.getType();
//
//        return modelMapper.map(instrumentList.stream()
//                .filter(instrument -> brand.isEmpty() || instrument.getBrand().equalsIgnoreCase(brand.get()))
//                .filter(instrument -> price.isEmpty() || instrument.getPrice()==price.get())
//                .collect(Collectors.toList()), targetType);

        return instrumentList.stream()
                .filter(instrument -> brand.isEmpty() || instrument.getBrand().equalsIgnoreCase(brand.get()))
                .filter(instrument -> price.isEmpty() || instrument.getPrice() == price.get())
                .map(i -> modelMapper.map(i, InstrumentDTO.class))
                .collect(Collectors.toList());
    }


    public InstrumentDTO addInstrument(CreateInstrumentCommand newInstrument) {
        Instrument instrument = new Instrument(id.incrementAndGet(), newInstrument.getBrand(),
                newInstrument.getType(), newInstrument.getPrice(), newInstrument.getPostDate());
        instrumentList.add(instrument);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public InstrumentDTO updateInstrument(long id, CreateInstrumentCommand newInstrument) {
        Instrument instrument = instrumentList.stream()
                .filter(i -> i.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
        instrument.setBrand(newInstrument.getBrand());
        instrument.setType(newInstrument.getType());
        instrument.setPrice(newInstrument.getPrice());
        instrument.setPostDate(newInstrument.getPostDate());
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteAll() {
        instrumentList.clear();
        id.set(0);
    }

    public InstrumentDTO updatePrice(long id, UpdatePriceCommand command) {
        Instrument instrument = instrumentList.stream()
                .filter(i -> i.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());

        if (instrument.getPrice() != command.getPrice()){
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }

        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public InstrumentDTO getInstrument(long id) {
        Instrument instrument = instrumentList.stream()
                .filter(i -> i.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());

        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteMovieByID(long id) {
        Instrument instrument = instrumentList.stream()
                .filter(i -> i.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());

        instrumentList.remove(instrument);
    }
}
