import React from 'react';
import Pagination from 'react-bootstrap/Pagination';
import styled from 'styled-components';

const PaginationStyled = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const CustomPagination = ({ currentPage, totalPage, onChangePage }) => {
  let items = [];
  for (let number = 1; number <= totalPage; number++) {
    items.push(
      <Pagination.Item
        key={number}
        active={number === currentPage}
        onClick={() => onChangePage(number)}
      >
        {number}
      </Pagination.Item>
    );
  }

  return (
    <PaginationStyled>
      <Pagination>{items}</Pagination>
    </PaginationStyled>
  );
};

export default CustomPagination;
