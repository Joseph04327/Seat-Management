<template>
  <div class="container">
    <h1>Seat Management</h1>

    <div class="toolbar">
      <label for="employeeSelect">Employee:</label>

      <select
        id="employeeSelect"
        v-model="selectedEmployeeId"
      >
        <option value="">
          -- Clear Mode --
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
          :class="{
            occupied: seat.occupied,
            empty: !seat.occupied
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
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";

import { getSeats } from "./api/seatApi";
import { getEmployees } from "./api/employeeApi";

const seats = ref([]);

const employees = ref([]);

const selectedEmployeeId = ref("");

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

onMounted(() => {
  loadSeats();
  loadEmployees();
});
</script>

<style scoped>
.container {
  padding: 20px;
}

.toolbar {
  margin-bottom: 30px;
}

.floor-row {
  margin-bottom: 20px;
}

.floor-title {
  font-weight: bold;
  margin-bottom: 10px;
}

.seat-row {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}

.seat {
  width: 300px;
  height: 60px;

  border-radius: 12px;

  display: flex;
  flex-direction: column;

  justify-content: center;
  align-items: center;

  font-size: 14px;
}

.empty {
  background-color: #d9d9d9;
  color: black;
}

.occupied {
  background-color: #ff0000;
  color: white;
}
</style>