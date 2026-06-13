<template>
  <div class="container">
    <h1>Seat Management</h1>

    <div class="toolbar">
      <label for="employeeSelect">人員選擇:</label>

      <select
        id="employeeSelect"
        v-model="selectedEmployeeId"
      >
        <option value="">
          -- 清除空位 --
        </option>

        <option
          v-for="employee in employees"
          :key="employee.employeeId"
          :value="employee.employeeId"
        >
          {{ employee.employeeId }} - {{ employee.name }}
        </option>
      </select>

    </div>

    <div
      v-for="(seatList, floorNo) in groupedSeats"
      :key="floorNo"
      class="floor-row"
    >

      <div class="seat-row">
        <div
          v-for="seat in seatList"
          :key="seat.seatId"
          class="seat"
          @click="selectSeat(seat)"
          :class="{
            occupied: seat.occupied && seat.seatId !== selectedSeatId,
            empty: !seat.occupied && seat.seatId !== selectedSeatId,
            selected: seat.seatId === selectedSeatId
          }"
        >
          <div v-if="!seat.occupied">
            {{ seat.floorNo }}樓: 座位
            {{ seat.seatNo }}
          </div>

          <div v-if="seat.occupied">
            {{ seat.floorNo }}樓: 座位
            {{ seat.seatNo }}
            [員編:{{ seat.employeeId }}]
          </div>
        </div>
      </div>
    </div>
    <div class="pending-action">

    <template v-if="selectedSeat">

      <template v-if="selectedEmployeeId">
        <div>
          指定人員
          <span class="highlight-employee">
            [員編:{{ selectedEmployeeId }}]
          </span>
          到
          <span class="highlight-seat">
            {{ selectedSeat.floorNo }}樓: 座位{{ selectedSeat.seatNo }}
          </span>
        </div>
      </template>

      <template v-else>
        <div>
          清除空位:
          <span class="highlight-seat">
            {{ selectedSeat.floorNo }}樓: 座位{{ selectedSeat.seatNo }}
          </span>
        </div>
      </template>

    </template>

  </div>
    <button
      class="submit-button"
      @click="submitChange"
    >
      送出
    </button>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";

import {
  getSeats,
  assignSeat,
  clearSeat
} from "./api/seatApi";
import { getEmployees } from "./api/employeeApi";

const seats = ref([]);

const employees = ref([]);

const selectedEmployeeId = ref("");

const selectedSeatId = ref(null);

const selectedSeat = computed(() => {
  return seats.value.find(
    seat => seat.seatId === selectedSeatId.value
  );
});

const loadSeats = async () => {
  try {
    const response = await getSeats();

    seats.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

const loadEmployees = async () => {
  try {
    const response = await getEmployees();

    employees.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

const groupedSeats = computed(() => {
  const map = {};

  seats.value.forEach((seat) => {
    if (!map[seat.floorNo]) {
      map[seat.floorNo] = [];
    }

    map[seat.floorNo].push(seat);
  });

  return map;
});

const selectSeat = (seat) => {

  const assignMode =
      selectedEmployeeId.value !== "";

  if (assignMode) {

    if (seat.occupied) {
      return;
    }

  } else {

    if (!seat.occupied) {
      return;
    }

  }

  selectedSeatId.value = seat.seatId;
};

const submitChange = async () => {

  if (selectedSeatId.value == null) {
    alert("Please select a seat");
    return;
  }

  try {

    if (selectedEmployeeId.value) {

      await assignSeat(
        selectedEmployeeId.value,
        selectedSeatId.value
      );

    } else {

      await clearSeat(
        selectedSeatId.value
      );

    }

    selectedSeatId.value = null;

    await loadSeats();

  } catch (error) {

    console.error(error);

    alert("Operation failed");
  }
};

onMounted(() => {
  loadSeats();
  loadEmployees();
});
</script>

<style scoped>
.container {
  padding: 20px;

  background-color: white;
  color: black;

  min-height: 100vh;
}

.toolbar {
  margin-bottom: 30px;

  display: flex;
  align-items: center;
  gap: 10px;

  justify-content: flex-start;
}

.floor-row {
  margin-bottom: 20px;
}

.floor-title {
  font-weight: bold;
  margin-bottom: 10px;
}

.seat-row {
  display: grid;
  grid-template-columns: repeat(4, 300px);
  gap: 10px;
}

.seat {
  width: 300px;
  height: 50px;

  border-radius: 12px;

  display: flex;
  flex-direction: column;

  justify-content: center;
  align-items: center;

  font-size: 16px;
  font-weight: bold;

  cursor: pointer;
}

.empty {
  background-color: #d9d9d9;
  color: black;
}

.occupied {
  background-color: #ff0000;
  color: white;
}

.selected {
  background-color: #90ee90;
  color: black;
}

.seat:hover {
  opacity: 0.85;
}

.submit-button {
  background-color: #1976d2;

  color: white;

  border: none;

  border-radius: 6px;

  padding: 8px 16px;

  cursor: pointer;

  font-weight: bold;
}

.submit-button:hover {
  background-color: #1565c0;
}

.pending-action {
  height: 30px;
  width: 1210px;
  margin-bottom: 20px;

  padding: 10px;
  border-radius: 6px;
  background-color: #f5f5f5;

  font-weight: bold;

  display: flex;
  align-items: center;
}

.highlight-employee {
  color: #1976d2;
  font-weight: bold;
  font-size: 16px;
}

.highlight-seat {
  color: #2e7d32;
  font-weight: bold;
  font-size: 16px;
}
</style>