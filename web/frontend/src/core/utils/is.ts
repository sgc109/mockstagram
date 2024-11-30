/* eslint-disable valid-jsdoc */
type Empty = undefined | null | '' | [];

export function isEmpty(value: unknown): value is Empty {
  return (
    value === undefined ||
    value === null ||
    (typeof value == 'string' && (value as string).trim() === '') ||
    (Array.isArray(value) && value.length === 0)
  );
}
