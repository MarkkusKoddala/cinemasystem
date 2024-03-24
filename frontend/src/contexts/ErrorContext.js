import React, { createContext, useContext, useState } from 'react';

const ErrorContext = createContext();

export const useError = () => useContext(ErrorContext);

export const ErrorProvider = ({ children }) => {
    const [error, setError] = useState(null);

    const triggerError = (errorMessage) => {
        setError(errorMessage);
        setTimeout(() => setError(null), 5000);
    };

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
        left: '50%', // Position the left edge of the div in the middle of the page
        transform: 'translateX(-50%)', // Move the div back to the left by half of its own width
        backgroundColor: 'red',
        color: 'white',
        padding: '10px',
        zIndex: 1000, // Ensure the popup is above other content
    }}>
        {message}
    </div>
);
