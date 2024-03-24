import React from 'react';
import styles from "./css/FilterResults.module.css"
import {useError} from "../../contexts/ErrorContext";
import {formatDateAndTime} from "../../utils/formatDateAndTime";
import {useNavigate} from "react-router-dom";

const FilterResults = ({searchResults, ticketQuantities, setTicketQuantities}) => {
    const {triggerError} = useError();
    const navigate = useNavigate();

    const handleQuantityChange = (movie, index, quantity) => {
        setTicketQuantities({
            [index]: {
                quantity: quantity,
                movie: movie
            }
        });
    };

    const handleMovieSelected = (index) =>  {
        const selectedTicket = ticketQuantities[index];

        if (!selectedTicket || selectedTicket.quantity <= 0) {
            triggerError("VIGA: Palun valige piletite arv valitud filmile");
            return;
        }

        navigate("/cinemaplan", {state: {ticketQuantity: selectedTicket.quantity, movie: selectedTicket.movie}})
    }

    return (
        <div className={styles.tableContainer}>
            <table className={styles.table}>
                <thead>
                <tr className={styles.tr}>
                    <th className={styles.th}>Filmi nimi</th>
                    <th className={styles.th}>Žanr</th>
                    <th className={styles.th}>Kuupäev- ja kellaaeg</th>
                    <th className={styles.th}>Keel</th>
                    <th className={styles.th}>IMDB skoor</th>
                    <th className={styles.th}> Piletite arv</th>
                    <th className={styles.th}></th>
                </tr>
                </thead>
                <tbody>
                {searchResults.map((movie, index) => (
                        <tr className={styles.tr} key={index}>
                            <td className={styles.td}>{movie.movieTitle}</td>
                            <td className={styles.td}>{movie.genre}</td>
                            <td className={styles.td}>{formatDateAndTime(movie.showTime)}</td>
                            <td className={styles.td}>{movie.language}</td>
                            <td className={styles.td}>{movie.imdbRating}</td>
                            <td className={styles.td}>
                                <input
                                    type="number"
                                    className={styles.ticketInput}
                                    value={ticketQuantities[index]?.quantity || ""}
                                    onChange={(e) => handleQuantityChange(movie, index, e.target.value)}
                                    min="1"
                                />
                                <button className={styles.addButton} onClick={() => handleMovieSelected(index)}>
                                    Vali film
                                </button>
                            </td>
                        </tr>

                    ))
                }
                </tbody>
            </table>
        </div>
    );
};

export default FilterResults;