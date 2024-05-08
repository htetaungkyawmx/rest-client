package org.jdc.restclient.ds;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@NoArgsConstructor

public class Customers implements Serializable {

    private List<Customer> customers = new ArrayList<>();

    public Customers(Iterable<Customer> iterable) {
        customers = StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customers=" + customers +
                '}';
    }
}
