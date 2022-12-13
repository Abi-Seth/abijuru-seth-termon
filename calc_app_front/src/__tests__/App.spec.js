describe('<Movielist>', () => {
    beforeEach(() => {
        cy.mount(<Home />);
    });

    it('The List of movies appends', () => {
        cy.get('[data-cy=result]').contains('');
        const formInput = cy.get('form input');
        formInput.should('have.value', '');
        formInput.type('6')
            .should('have.value', 6);
        cy.get('form button').click();
        formInput.clear();
        formInput.type('4')
            .should('have.value', '4');
        formInput.type('+')
            .should('have.value', '+');
        cy.get('form button').click();
        cy.get('[data-cy=result]').children().should('have.length', 1);
    })
})