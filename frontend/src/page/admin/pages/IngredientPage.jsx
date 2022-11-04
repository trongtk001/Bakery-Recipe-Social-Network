import React, { useEffect } from 'react';
import styled from 'styled-components';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

import Pagination from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';

import ActionTable from '../components/tables/ActionTable';
import CustomPagination from '../components/common/CustomPagination';
import axiosClient from '../api/axiosClient';
import { ingredientActions } from '../features/ingredients/ingredientSlice';
import Stack from 'react-bootstrap/Stack';

let active = 2;
let items = [];
for (let number = 1; number <= 5; number++) {
  items.push(
    <Pagination.Item key={number} active={number === active}>
      {number}
    </Pagination.Item>
  );
}

const IngredientPageStyled = styled.div`
  h1 {
    margin-bottom: 12px;
  }
`;

const IngredientPage = () => {
  const tableHeaders = ['ID', 'Name', 'Quantity', 'Unit'];
  const isLoading = useSelector((state) => state.ingredients.isLoading);
  const ingredients = useSelector((state) => state.ingredients.data);
  const ingredientFilter = useSelector((state) => state.ingredients.filter);
  const dispatch = useDispatch();

  useEffect(() => {
    (async () => {
      try {
        const response = await axiosClient.get(
          `ingredient?page=${ingredientFilter.page}&size=${ingredientFilter.limit}`
        );
        dispatch(ingredientActions.setState(response));
      } catch (error) {
        console.log(error);
      }
    })();
  }, [ingredientFilter.page]);

  const handleChangePage = (page) => {
    const convertedPageValue = +page;
    if (typeof convertedPageValue == 'number') dispatch(ingredientActions.setPage(page));
  };

  const handleRemove = () => {};

  return (
    <IngredientPageStyled>
      <Stack direction='horizontal'>
        <h1>Manage Ingredients</h1>
        <Button>Add</Button>
      </Stack>
      <ActionTable headers={tableHeaders}>
        {!isLoading &&
          ingredients.map((ingredient) => (
            <tr key={ingredient.id}>
              <td>{ingredient.id}</td>
              <td>{ingredient.ingredients}</td>
              <td>{ingredient.quantity}</td>
              <td>{ingredient.unit}</td>
              <td>
                <Button
                  variant='primary'
                  style={{ marginRight: '12px' }}
                  as={Link}
                  to={`/ingredients/edit/${ingredient.id}`}
                >
                  Edit
                </Button>
                <Button variant='danger' onClick={() => handleRemove(ingredient.id)}>
                  Remove
                </Button>
              </td>
            </tr>
          ))}
      </ActionTable>
      <CustomPagination
        totalPage={ingredientFilter.totalPage}
        currentPage={ingredientFilter.page}
        onChangePage={handleChangePage}
      />
    </IngredientPageStyled>
  );
};

export default IngredientPage;
