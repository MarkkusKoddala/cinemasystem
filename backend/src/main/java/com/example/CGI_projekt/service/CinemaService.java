package com.example.CGI_projekt.service;


import com.example.CGI_projekt.DTO.CinemaHallDTO;
import com.example.CGI_projekt.DTO.SeatDTO;
import com.example.CGI_projekt.enums.SeatStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CinemaService {

    public CinemaService(){
    }

    public CinemaHallDTO getCinamonHallInfo(int ticketQuantity) {

        int rows = 10;
        int columns = 15;

        //funktsioon, mis genereerib kinoplaani
        List<SeatDTO> seatsPlan = generateHallSeatsPlan(rows, columns);


        //void tüüpi funktsioon, mis lisab istmete soovituse seatsPlanile
        recommendSeats(seatsPlan, ticketQuantity, rows, columns);

        //tagastame controllerile DTO klassi, kus on ka värskelt töödeldud seatsPlan koos soovitatud kothadeg
        return new CinemaHallDTO(rows, columns, seatsPlan);
    }



    //funktsioon, mis geneerib vastavalt ridade ja veergude parameetritele kinoplaani, kusjuures 20%-le istmetele määrab, et kohad on juba ära võetud
    public List<SeatDTO> generateHallSeatsPlan(int rows, int columns) {

        List<SeatDTO> seatsPlan = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                String id = row + String.valueOf((char) ('A' + col - 1 ));
                SeatStatus status = Math.random() > 0.5 ? SeatStatus.OCCUPIED : SeatStatus.AVAILABLE;
                seatsPlan.add(new SeatDTO(id,row,col,status));
            }
        }
        return seatsPlan;
    }

    private void recommendSeats(List<SeatDTO> seats, int ticketQuantity, int rows, int columns) {


        //Esialgu filtreerime välja toolid, mis on vabad ning seejärel sorteerime neid. Sorteerides võrdleme istmete omadusi, mille arvutame välja kasutades
        // abifunktsiooni. Abifunktsioon võtab arvesse istme asukohta keskpunkti suhtes ning korrutab läbi kaaludega, mida saab arvestab ülesande eelistusi.
        List<SeatDTO> availableSeats = seats.stream()
                .filter(seat -> seat.getStatus() == SeatStatus.AVAILABLE).sorted((seat1, seat2) -> {
                    double score1 = calculateSeatScore(seat1, rows, columns);
                    double score2 = calculateSeatScore(seat2, rows, columns);
                    return Double.compare(score1, score2);
                }).toList();


        //Nüüd on meil sorteeritud list, kus esimestel kohtadel on kõige sobivamad positsioonid. Käime selle listi läbi, kuni leiame sobivad järjestikused kohad.
        for (SeatDTO seat : availableSeats) {

            //Abifunktsioon, mis otsib esialgsest seats listist, kas on olemas sobivad järjestikused kohad
            if (areConsecutiveSeatsAvailable(seats, seat, ticketQuantity, columns)) {

                //Kui kohad on leitud, siis muudame nende staatuse "SOOVITATUD" ning väljume tsüklist. Tsükli väljudes oleme ka töö lõptenud ja sobivad kohad leidnud.
                for (int i = 0; i < ticketQuantity; i++) {
                    seats.get(seats.indexOf(seat) + i).setStatus(SeatStatus.RECOMMENDED);
                }
                break;
            }
        }
    }



    private double calculateSeatScore(SeatDTO seat, int rows, int columns) {
        // Kaal, mis määrab, kui oluline on rea kaugust ekraanist.
        // Mida madalam on kaal, seda olulisem on, et rida säiliks, aga kohti otsitakse rohkem samadelt ridadelt või ridadelt, mis on keskpunktile võimalikult lähedal.
        double columnDistanceWeight = 0.1;
        // Kaal, mis määrab omaduse, kui oluline on, et istekohad asuksid rea keskel. Mida madalam on kaal, seda olulisem on,
        // et istmed asuksid võimalikult rea keskel, samal ajal väheneb olulisus, kui kaugel või lähedal see rida ekraanist / algpunktist on.
        double rowDistanceWeight = 0.9;


        //Keskpunkt, mis on arvutatud loogikal, et "ideaalne" koht võiks olla keskel, saali tagumises kolmandikus.
        int xMidPoint = columns / 2;
        int yMidPoint = rows / 3 * 2 + 1 ;

        //Arvutame veeru ja rea kauguse absoluutväärtuse keskpunkti suhtes
        double columnDistance = Math.abs(seat.getCol() - xMidPoint);
        double rowDistance = Math.abs(seat.getRow() - yMidPoint);


        //rakendame kaugustele kaalusid, et iseloomustada koha head asukohta
        return (columnDistance * columnDistanceWeight) + (rowDistance * rowDistanceWeight);
    }



    private boolean areConsecutiveSeatsAvailable(List<SeatDTO> seats, SeatDTO startSeat, int quantity, int columns) {

        int consecutiveFound = 1;

        //otsime välja startSeati indeksi seats listist
        int startIndex = seats.indexOf(startSeat);

        //Vaatame läbi järgnevad kohad samast reast. Tingimusteks on, et antud reas on veel üldse piisavalt kohti, et soovitud arv inimesi järjest istuma panna ja et piisavalt kohti pole juba leitud.
        for (int i = startIndex + 1; startSeat.getCol() + quantity <= columns &&  consecutiveFound < quantity; i++) {

            //järgmine koht seats listist
            SeatDTO nextSeat = seats.get(i);

            //võrdlen, kas need kohad on ikka samas reas ning kas koht on vaba, kui on siis märgin ära, kas sobiv koht on leitud
            if (nextSeat.getRow() == startSeat.getRow() && nextSeat.getStatus() == SeatStatus.AVAILABLE) {
                consecutiveFound++;
            } else {
                break; // kui peaks leiduma kasvõi üks mittesobiv koht, siis väljutakse
            }
        }

        //tagastame, kas startSeatist alates leidub sobiv arv kohti, mis järgnevad sellele
        return consecutiveFound == quantity;
    }



}
