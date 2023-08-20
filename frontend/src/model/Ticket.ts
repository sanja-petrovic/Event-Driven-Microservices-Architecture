/**private UUID id;
    private UUID userId;
    private UUID concertId;
    private int seat;
    private String status; */

export interface Ticket {
  id: string;
  userId?: string;
  concertId: string;
  seat: number;
  status: 'AVAILABLE' | 'SELECTED' | 'PURCHASED';
}
