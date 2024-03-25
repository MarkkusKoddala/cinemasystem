import React, { createContext, useContext, useState } from 'react';


const ErrorContext = createContext();

export const useError = () => useContext(ErrorContext);


//Provideri eesmärk on parandada error-handlingut ning luua ühtne süsteem vigade kuvamiseks.
export const ErrorProvider = ({ children }) => {
    const [error, setError] = useState(null);

    const triggerError = (errorMessage) => {
        setError(errorMessage);
        setTimeout(() => setError(null), 5000);
    };


    //kui error on leitud ja see kutsutakse välja, siis kuvatakse error
    return (
        <ErrorContext.Provider value={{ error, triggerError }}>
            {children}
            {error && <ErrorPopup message={error} />}
        </ErrorContext.Provider>
    );
};

const ErrorPopup = ({ message }) => (
    <div style={{
        position: 'fixed',
        top: '20px',
        left: '50%',
        transform: 'translateX(-50%)',
        backgroundColor: 'red',
        color: 'white',
        padding: '10px',
        zIndex: 1000,
    }}>
        {message}
    </div>
);
