import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import { Controller, useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import axiosClient from '../api/axiosClient';

const defaultValues = {
  name: null,
  username: '',
  password: '',
  email: '',
  birthday: '',
  avatar: '',
  roles: '',
};

const AccountEditPage = () => {
  const params = useParams();
  const [user, setUser] = useState(null);
  const { reset, control, handleSubmit } = useForm({
    defaultValues: defaultValues,
  });

  const userId = params.userId;

  const onSubmit = async (formValues) => {
    if (formValues) {
      try {
        if (!Array.isArray(formValues.roles)) {
          formValues.roles = formValues.roles.split(',');
        }

        if (userId) {
          await axiosClient.put(`/admin/member/${userId}`, {
            ...formValues,
          });
        } else {
          await axiosClient.post(`/member`, {
            ...formValues,
          });
        }
      } catch (error) {
        console.log(error);
      }
    }
  };

  useEffect(() => {
    (async () => {
      try {
        if (userId) {
          const response = await axiosClient.get(`/admin/member/${userId}`);
          delete response.token;
          reset(response);
          setUser(response);
        } else {
          setUser(defaultValues);
        }
      } catch (error) {}
    })();
  }, []);

  return (
    <Container>
      {!user && <h3>User is not found</h3>}
      {user && (
        <Form onSubmit={handleSubmit(onSubmit)}>
          <Form.Group className='mb-3' controlId='username'>
            <Form.Label>Username</Form.Label>
            <Controller
              id='username'
              control={control}
              name='username'
              render={({ field }) => {
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          <Form.Group className='mb-3' controlId='password'>
            <Form.Label>Password</Form.Label>
            <Controller
              control={control}
              name='password'
              render={({ field }) => {
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          <Form.Group className='mb-3' controlId='name'>
            <Form.Label>Full name</Form.Label>
            <Controller
              id='name'
              control={control}
              name='name'
              render={({ field }) => {
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          <Form.Group className='mb-3' controlId='email'>
            <Form.Label>Email</Form.Label>
            <Controller
              id='email'
              control={control}
              name='email'
              render={({ field }) => {
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          <Form.Group className='mb-3' controlId='birthday'>
            <Form.Label>Birthday</Form.Label>
            <Controller
              id='dob'
              control={control}
              name='dob'
              render={({ field }) => {
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          <Form.Group className='mb-3' controlId='avatar'>
            <Form.Label>Avatar</Form.Label>
            <Controller
              id='avatar'
              control={control}
              name='avatar'
              render={({ field }) => {
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          <Form.Group className='mb-3' controlId='roles'>
            <Form.Label>Roles</Form.Label>
            <Controller
              id='roles'
              control={control}
              name='roles'
              render={({ field }) => {
                if (Array.isArray(field)) {
                  field.value = field.value.join(',');
                }
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          <Button variant='primary' type='submit'>
            Submit
          </Button>
        </Form>
      )}
    </Container>
  );
};

export default AccountEditPage;
