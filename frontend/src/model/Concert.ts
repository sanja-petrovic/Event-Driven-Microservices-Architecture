export default interface Concert {
  id: string;
  name: string;
  venueId: string;
  performer: string;
  dateTime: Date;
  attendance: number;
}
